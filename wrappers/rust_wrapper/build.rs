use {std::env, winresource};

fn main() {
    if env::var_os("CARGO_CFG_WINDOWS").is_some() {
        winresource::WindowsResource::new()
            .set_icon("../../images/mars.ico")
            .compile()
            .unwrap();
    }
}