package ru.job4j.helperclasses;


public class Test {
    public static void main(String[] args) {
        System.out.println(new ReadProperties2().read());
        System.out.println(new ReadProperties3().read());
    }
    public static class ReadProperties2 {
        public int read() {
            String path = "rabbit.properties";
            Config config = new Config(path);
            config.load();
            return Integer.parseInt(config.value("rabbit.interval"));
        }
    }
    public static class ReadProperties3 {
        public String read() {
            String path = "rabbit.properties";
            Config config = new Config(path);
            config.load();
            return config.value("rabbit.interval");
        }
    }
}
