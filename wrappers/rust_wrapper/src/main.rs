#![windows_subsystem = "windows"]

use std::{env, process};

fn main() {
    let executable_path = env::current_exe().expect("Failed to get current executable path.");
    let java_dl_url = "https://www.java.com/download/";
    let java_executable = if env::consts::OS.contains("win") {
        "javaw"
    } else {
        "java"
    };
    let result = process::Command::new(java_executable)
        .arg("-jar")
        .arg(executable_path)
        .args(env::args_os().skip(1))
        .spawn();

    if let Ok(mut child) = result {
        child.wait().expect("Couldn't wait for child");
    } else {
        open::that(java_dl_url).expect("Couldn't open browser");
    }
}
