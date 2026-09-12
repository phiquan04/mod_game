package core;

import java.io.IOException;
import java.util.HashMap;

public class Config {
    private static Config instance;

    public boolean debug;
    public String mysql_host;
    public String mysql_database;
    public String mysql_user;
    public String mysql_pass;
    public int server_port;
    public int exp;
    public boolean server_admin;

    public static Config gI() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public void load() throws IOException {
        final byte[] ab = Util.loadfile("htth.conf");
        if (ab == null) {
            System.out.println("Config file not found!");
            System.exit(0);
        }
        final String data = new String(ab);
        final HashMap<String, String> configMap = new HashMap<String, String>();
        final StringBuilder sbd = new StringBuilder();
        boolean bo = false;
        for (int i = 0; i <= data.length(); ++i) {
            final char es;
            if (i == data.length() || (es = data.charAt(i)) == '\n') {
                bo = false;
                final String sbf = sbd.toString().trim();
                if (sbf != null && !sbf.equals("") && sbf.charAt(0) != '#') {
                    final int j = sbf.indexOf(58);
                    if (j > 0) {
                        final String key = sbf.substring(0, j).trim();
                        final String value = sbf.substring(j + 1).trim();
                        configMap.put(key, value);
                        System.out.println("config: " + key + ": " + value);
                    }
                }
                sbd.setLength(0);
            } else {
                if (es == '#') {
                    bo = true;
                }
                if (!bo) {
                    sbd.append(es);
                }
            }
        }
        if (configMap.containsKey("port")) {
            this.server_port = Integer.parseInt(configMap.get("port"));
        } else {
            this.server_port = 2239;
        }
        if (configMap.containsKey("debug")) {
            this.debug = Boolean.parseBoolean(configMap.get("debug"));
        } else {
            this.debug = false;
        }
        if (configMap.containsKey("mysql-host")) {
            this.mysql_host = configMap.get("mysql-host");
        } else {
            this.mysql_host = "127.0.0.1";
        }
        if (configMap.containsKey("mysql-user")) {
            this.mysql_user = configMap.get("mysql-user");
        } else {
            this.mysql_user = "root";
        }
        if (configMap.containsKey("mysql-password")) {
            this.mysql_pass = configMap.get("mysql-password");
        } else {
            this.mysql_pass = "12345678";
        }
        if (configMap.containsKey("mysql-database")) {
            this.mysql_database = configMap.get("mysql-database");
        } else {
            this.mysql_database = "database";
        }
        if (configMap.containsKey("exp")) {
            this.exp = Integer.parseInt(configMap.get("exp"));
        } else {
            this.exp = 1;
        }
        if (configMap.containsKey("serveradmin")) {
            this.server_admin = Boolean.parseBoolean(configMap.get("serveradmin"));
        } else {
            this.server_admin = false;
        }
    }
}
