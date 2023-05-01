package com.cognixia.pokemonApiRunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import com.cognixia.pokemonApiRunner.model.Caught;
import com.cognixia.pokemonApiRunner.model.Pokemon;
import com.cognixia.pokemonApiRunner.service.*;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Scanner sc;
	private static String curUser;
	private static String cookiePath = "./resources/cookie.txt";
	private static String iconPath = "./resources/icon.txt";
	
	private static void readCookie() {
    	try(BufferedReader reader = new BufferedReader(new FileReader(new File(cookiePath)))) {
			String savedUser = reader.readLine();
			curUser = savedUser;
			if (curUser != null) {
				System.out.println("Welcome " + curUser + " to our pokemon collection app!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    private static void writeCookie() {
    	//update cookies
		try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
			if (curUser == null) {
				writer.write("");
			} else {
				writer.write(curUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void icon() {
    	try(BufferedReader reader = new BufferedReader(new FileReader(new File(iconPath)))) {
    		String line = reader.readLine();
    		while (line != null) {
    			System.out.println(line);
    			line = reader.readLine();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static StringBuilder tableFormat(String[] commands){
    	int commandMaxLen = 25;
    	int breakerLen = 35;
    	StringBuilder table = new StringBuilder();
    	String breaker = "=".repeat(breakerLen) + "\n";
    	table.append(breaker);
    	for (int i = 0; i < commands.length; i++) {
    		table.append( String.format("| %-" + 3 +"s | %-" + commandMaxLen +"s |\n", i+1, commands[i]) );
    	}
    	table.append(breaker);
		return table;
	}
	
    public static void main( String[] args ) throws IllegalArgumentException, IllegalAccessException
    {
    	runner();
    }
    
    public static void runner() {
    	sc = new Scanner(System.in);
    	
    	icon();
		
    	readCookie();
				
    	boolean running = true;
		while (running) {
			if (curUser == null) {
				System.out.print("Logged Out> ");
			} else {
				System.out.print(curUser + "> ");
			}
			String command = sc.nextLine(); 
			Caught[] caughtCollection;
			Pokemon[] pokemonCollection;
			try {
				switch(command.toLowerCase()) {
					case "help":
						String[] commands = {"login", "create user", "logout", "get collection", "catch pokemon", "level pokemon", "search pokemon", "get uncompleted pokemon", "get pokemon", "get uncaught pokemon", "populate db", "clear db", "exit"};
						System.out.println(tableFormat(commands) );

						break;
						
					case "1":
					case "login":
						curUser = UserService.login(sc).getUsername();
						writeCookie();
						break;
					
					case "2":
					case "create user":
						curUser = UserService.create(sc).getUsername();
						writeCookie();
						break;
						
					case "3":
					case "logout":
						curUser = null;
						writeCookie();
						break;
						
					case "4":
					case "get collection":
						caughtCollection = CaughtService.getCollection(curUser);
						System.out.println(CaughtService.tableFormat(caughtCollection));
						break;
					case "5":
					case "catch pokemon":
						CaughtService.catchPokemon(sc, curUser);
						break;
					case "6":
					case "level pokemon":
						CaughtService.level(sc, curUser);
						break;
					case "7":
					case "search pokemon":
						Caught found = CaughtService.search(sc, curUser);
						Caught[] foundReal = {found};
						System.out.println(CaughtService.tableFormat( foundReal ));
						break;
					case "8":
					case "get uncompleted pokemon":
						caughtCollection = CaughtService.getUncompleted(curUser);
						System.out.println(CaughtService.tableFormat(caughtCollection));
						break;
						
					case "9":
					case "get pokemon":
						pokemonCollection = CaughtService.getAll();
						for (Pokemon pokemon : pokemonCollection) {
							System.out.println(pokemon.getPokemonName());
						}
						break;
					
					case "10":
					case "get uncaught pokemon":
						pokemonCollection = CaughtService.getUncaught(curUser);
						for (Pokemon pokemon : pokemonCollection) {
							System.out.println(pokemon.getPokemonName());
						}
						break;
					
					case "11":
					case "populate db":
						CaughtService.populate();
						break;
					case "12":
					case "clear db":
						CaughtService.truncate();
						break;
					case "13":
					case "exit":
						running = false;
						break;
			
					
					default:
						System.out.println("awaiting command");
						break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		sc.close();
		System.out.println("Exiting, thanks for playing");
    }
}
