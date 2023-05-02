package com.cognixia.pokemonApiRunner.service;

public class TextColor {
	public static Object clear;

	public static void clear() {
		System.out.print("\u001B[00m");
	}
	public static void yellow() {
		System.out.print("\u001B[33m");
	}
	public static String yellowText() {
		return "\u001B[33m";
	}
	public static void green() {
		System.out.print("\u001B[32m");
	}
	public static void blue() {
		System.out.print("\u001B[36m");
	}
	public static String blueText() {
		return "\u001B[36m";
	}
	public static String clearText() {
		return "\u001B[00m";
	}
	
	public static void red() {
		System.out.print("\u001B[31m");
	}
	public static String redText() {
		return "\u001B[31m";
	}
	
	public static String greenText() {
		return "\u001B[32m";
	}
}

