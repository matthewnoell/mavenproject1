/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.raytheon.mavenproject1;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args) {
        CommandLine commandLine;
        Option option_A = Option.builder("A").argName("opt3").hasArg().desc("The A option").build();
        Option option_r = Option.builder("r").argName("opt1").hasArg().desc("The r option").build();
        Option option_S = Option.builder("S").argName("opt2").hasArg().desc("The S option").build();
        Option option_test = Option.builder().longOpt("test").desc("The test option").build();
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addOption(option_A);
        options.addOption(option_r);
        options.addOption(option_S);
        options.addOption(option_test);
        
        String header = "               [<arg1> [<arg2> [<arg3> ...\n       Options, flags and arguments may be in any order";
        String footer = "This is DwB's solution brought to Commons CLI 1.3.1 compliance (deprecated methods replaced)";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CLIsample", header, options, footer, true);
        
        String[] testArgs =
        { "-r", "opt1", "-S", "opt2", "arg1", "arg2",
          "arg3", "arg4", "--test", "-A", "opt3", };
        
        try
        {
            commandLine = parser.parse(options, testArgs);

            if (commandLine.hasOption("A"))
            {
                System.out.print("Option A is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("A"));
            }

            if (commandLine.hasOption("r"))
            {
                System.out.print("Option r is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("r"));
            }

            if (commandLine.hasOption("S"))
            {
                System.out.print("Option S is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("S"));
            }

            if (commandLine.hasOption("test"))
            {
                System.out.println("Option test is present.  This is a flag option.");
            }
            
            {
                String[] remainder = commandLine.getArgs();
                System.out.print("Remaining arguments: ");
                for (String argument : remainder)
                {
                    System.out.print(argument);
                    System.out.print(" ");
                }

                System.out.println();
            }
        }
        catch (ParseException exception)
        {
            System.out.print("Parse error: ");
            System.out.println(exception.getMessage());
        }

        HeadlessSimple headlessSimple = new HeadlessSimple();
        headlessSimple.script();
    }
}
