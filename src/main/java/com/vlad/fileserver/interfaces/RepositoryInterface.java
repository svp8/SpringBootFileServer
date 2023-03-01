package com.vlad.fileserver.interfaces;

import java.io.File;
import java.util.ArrayList;

public interface RepositoryInterface {
    public ArrayList<File> findAll();
    public File findByName();
}
