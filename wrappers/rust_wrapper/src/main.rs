#![windows_subsystem = "windows"]

use std::{env, process};

fn main() {
    let executable_path = env::current_exe().expect("Failed to get current executable path.");
    let java_executable = if env::consts::OS.contains("win") {
        "javaw"
    } else {
        "java"
    };
    let mut child = process::Command::new(java_executable)
        .arg("-jar")
        .arg(executable_path)
        .spawn()
        .expect("Failed to spawn JVM.");
    child.wait().expect("Couldn't wait for child");
}
