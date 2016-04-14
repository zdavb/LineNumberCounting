package com.dacas.main;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by dave on 2016/4/14.
 */
public class Rule {
    private List<File> includeFiles;
    private Set<String> excludeFiles;
    private File outputFile;
    private Set<String> suffixes;

    public Rule(){
        includeFiles = new LinkedList<>();
        excludeFiles = new HashSet<>();
        suffixes = new HashSet<>();
    }

    public List<File> getIncludeFiles() {
        return includeFiles;
    }

    public void setIncludeFiles(List<File> includeFiles) {
        this.includeFiles = includeFiles;
    }

    public Set<String> getExcludeFiles() {
        return excludeFiles;
    }

    public void setExcludeFiles(Set<String> excludeFiles) {
        this.excludeFiles = excludeFiles;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public Set<String> getSuffixes() {
        return suffixes;
    }

    public void setSuffixes(Set<String> suffixes) {
        this.suffixes = suffixes;
    }
}
