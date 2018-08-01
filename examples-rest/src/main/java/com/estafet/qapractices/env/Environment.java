/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.env;

import com.estafet.qapractices.exceptions.TestException;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pesho on 15-Sep-17.
 */

/**
 * This file configures the environment. Do not make changes in the existing code.
 * Currently  it only loads an yml file where environmental data should be stored: URLs, credentials, etc.
 */
public class Environment {
    public static final String FRAMEWORK_DIR = ".";
    private Map<String, Object> environment = Maps.newHashMap();

    @Inject
    public Environment() {
        init();
    }

    private static void listFiles(final File parentDirectory, final String regex, final List<File> files) {
        final File listFiles[] = parentDirectory.listFiles();
        if (files != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    listFiles(listFiles[i], regex, files);
                } else {
                    if (listFiles[i].getName().matches(regex)) {
                        files.add(listFiles[i]);
                    }
                }
            }
        }
    }

    public String getProperty(final String key) {
        final String property = (String) environment.get(key);
        if (property == null) {
            throw new TestException("Cannot find property for '" + key + "' in env.yaml");
        }
        return property;
    }

    private void init() {
        final File file = getEnvironmentFile();
        final String yamlFile = "defaultTestEnvironment";
        try {
            final InputStream inputStream = new FileInputStream(file);
            final Yaml yaml = new Yaml();
            final Map<String, Map<String, Object>> data = ((Map<String, Map<String, Object>>) yaml.load(inputStream));

            environment = (Map<String, Object>) data.get(yamlFile);

            if (environment == null) {
                throw new TestException("The environment [" + yamlFile + "] does not exist in the file ["
                        + file.getAbsolutePath() + "].");
            }
            inputStream.close();
        } catch (YAMLException e) {
            throw new TestException(e);
        } catch (IOException e) {
            throw new TestException(e);
        }
    }

    private File getEnvironmentFile() {
        final File[] files = listFiles(new File(FRAMEWORK_DIR), "^environment.ya?ml$");
        return files[0];
    }

    public File[] listFiles(final File parentDirectory, final String regex) {
        final List<File> matchedFiles = new ArrayList<File>();
        listFiles(parentDirectory, regex, matchedFiles);
        return matchedFiles.toArray(new File[matchedFiles.size()]);
    }
}
