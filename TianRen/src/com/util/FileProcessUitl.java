// 
// 
// 

package com.util;

import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import java.io.File;

public class FileProcessUitl
{
    public String processFileUpload(final String path, final File[] file, final String[] fileName) {
        if (fileName == null) {
            return null;
        }
        try {
            final StringBuffer file_location = new StringBuffer();
            final ServletContext context = ServletActionContext.getServletContext();
            for (int i = 0; i < file.length; ++i) {
                final String targetDirectory = context.getRealPath(path).trim();
                final String extName = fileName[i].substring(fileName[i].indexOf("."));
                final String GenericFileName = String.valueOf(System.nanoTime()) + extName;
                final String location = String.valueOf(ServletActionContext.getRequest().getContextPath()) + path + "/" + GenericFileName;
                final File target = new File(targetDirectory, GenericFileName);
                FileUtils.copyFile(file[i], target);
                file_location.append(location).append(",");
            }
            final String locations = file_location.deleteCharAt(file_location.lastIndexOf(",")).toString();
            return locations;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
