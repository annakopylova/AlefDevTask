package dev.anna.core.navigation

sealed class Screen

class ContentScreen(val url: String): Screen()