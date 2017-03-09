package pl.consdata.security.cryptofail;

import org.apache.commons.cli.*;

public class CmdLineConfiguration {

    private static final String MODE = "mode";

    private static final String MODE_ENCRYPT = "encrypt";

    private static final String MODE_DECRYPT = "decrypt";

    private static final String KEY = "key";

    private static final String INPUT = "input";

    private static final String HELP = "help";

    private CommandLine commandLine;

    public CmdLineConfiguration(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = initOptions();
        commandLine = parser.parse(options, args);

        if (commandLine.hasOption("h")) {
            HelpFormatter formater = new HelpFormatter();
            formater.printHelp("java -jar cryptofail.jar [options]", options);
        }
    }

    private Options initOptions() {
        Options options = new Options();
        options.addOption("k", KEY, true, "encryption key");
        options.addOption("i", INPUT, true, "input file");
        options.addOption("m", MODE, true, "mode: encrypt|decrypt");
        options.addOption("h", HELP, false, "print this help");
        return options;
    }

    public String getKey() {
        return commandLine.getOptionValue(KEY);
    }

    public String getInput() {
        return commandLine.getOptionValue(INPUT);
    }

    public String getMode() {
        return commandLine.getOptionValue(MODE);
    }

    public boolean isEncrypt() {
        return MODE_ENCRYPT.equals(getMode());
    }

    public boolean isDecrypt() {
        return MODE_DECRYPT.equals(getMode());
    }
}
