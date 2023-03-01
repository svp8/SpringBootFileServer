package com.vlad.fileserver.models;

import com.vlad.fileserver.interfaces.RepositoryInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileRepository implements RepositoryInterface {
    @Override
    public File findByName() {
        return null;
    }

    @Override
    public ArrayList<File> findAll() {
        File folder=new File(".//files");
        File[] files=folder.listFiles();
        ArrayList<File> filenames=new ArrayList<File>();
        Arrays.stream(files).forEach(fil -> {
            System.out.println(fil.getName());
            filenames.add(fil);});
        return filenames;
    }
    public class Tset{

    }
}
