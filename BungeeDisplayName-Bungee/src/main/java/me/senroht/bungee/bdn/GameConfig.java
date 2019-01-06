package me.senroht.bungee.bdn;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.YamlConfiguration;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class GameConfig extends YamlConfiguration {
    protected GameConfig(Path path, YamlProperties properties) {
        super(path, properties);
    }
    @Comment({" ", " Default Config.", " ", "Config_Version: Please do not change."})
    private Integer Config_Version = 1;

    @Comment({"", " ", " The tag that the plugin will show with commands.", " "})
    private String Plugin_Tag = "&f&l[&9BDN&f&l]&r ";

    @Comment({"", " ", " Permissions.", " ", "Need permissions: will use a permissions plugin, if you do not have a permission plugin please set this to false.", "Permission nodes are found on the spigot page."})
    private Boolean Need_Permissions = true;

    @Comment({"", " ", " Whitelist Servers.", " ", "Whitelist_On: If true displaynames will only work on those servers."})
    private Boolean Whitelist_On = false;

    @Comment({"Whitelisted_Servers: Server names from your bungee config."})
    private List<String> Whitelisted_Servers = Arrays.asList("Server", "Names", "Here");

    @Comment({"", " ", " Nicknames.", " ", "Use_Prefix: Use prefix before the display name."})
    private Boolean Use_Prefix = true;

    @Comment({"Prefix: prefix before the display name."})
    private String Prefix = "&9+";

    @Comment({"Length_Limit: if true will check the length."})
    private Boolean Length_Limit = true;

    @Comment({"Length: will disallow any nicknames with more then that amount."})
    private Integer Length = 25;

    @Comment({"Use_Colors: will convert color codes if set to true."})
    private Boolean Use_Colors = true;

    @Comment({"Allow_Spaces: will allow more then one word in display names."})
    private Boolean Allow_Spaces = true;

    //@Comment({"", " ", " 1.7 Support.", " ", "Change the length check to support 1.7", "1.7 does not support names longer than 16 characters, color codes included"})
    //private Boolean Retro_Support = true;

    public String getString(String str){
        switch(str){
            case "Plugin_Tag":
                return Plugin_Tag;
            case "Prefix":
                return Prefix;
            case "Length":
                return Length.toString();

        }
        return null;
    }
    public Boolean getBoolean(String str){
        switch(str){
            case "Need_Permissions":
                return Need_Permissions;
            case "Whitelist_On":
                return Whitelist_On;
            case "Use_Prefix":
                return Use_Prefix;
            case "Length_Limit":
                return Length_Limit;
            case "Use_Colors":
                return Use_Colors;
            case "Allow_Spaces":
                return Allow_Spaces;

        }
        return null;
    }

    public String get(String str){
        switch(str){
            case "Need_Permissions":
                return Need_Permissions.toString();
            case "Whitelist_On":
                return Whitelist_On.toString();
            case "Use_Prefix":
                return Use_Prefix.toString();
            case "Length_Limit":
                return Length_Limit.toString();
            case "Use_Colors":
                return Use_Colors.toString();
            case "Allow_Spaces":
                return Allow_Spaces.toString();
            case "Plugin_Tag":
                return Plugin_Tag;
            case "Prefix":
                return Prefix;
            case "Length":
                return Length.toString();

        }
        return null;
    }

    public List<String> getStringList(String str){
        switch(str){
            case "Whitelisted_Servers":
                return Whitelisted_Servers;
        }
        return null;
    }

    public Integer getInt(String str){
        switch (str){
            case "Length":
                return Length;
        }
        return null;
    }
    public void set(String str, String arg){
        switch(str){
            case "Plugin_Tag":
                Plugin_Tag = arg;
                break;
            case "Prefix":
                Prefix = arg;
                break;
            case "Length":
                Length = parseInt(arg);
                break;
        }
    }
    public void set(String str, Boolean arg){
        switch(str){
            case "Need_Permissions":
                Need_Permissions = arg;
                break;
            case "Whitelist_On":
                Whitelist_On = arg;
                break;
            case "Use_Prefix":
                Use_Prefix = arg;
                break;
            case "Length_Limit":
                Length_Limit = arg;
                break;
            case "Use_Colors":
                Use_Colors = arg;
                break;
            case "Allow_Spaces":
                Allow_Spaces = arg;
                break;

        }
    }
    public void set(String str, Integer arg){
        switch (str){
            case "Length":
                Length = arg;
                break;
        }
    }
}
