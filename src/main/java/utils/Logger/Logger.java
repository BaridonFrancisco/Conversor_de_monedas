package utils.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

public class Logger {
    private String path;
    private String fileName;
    private String directory;
    private File file;

    Logger(Builder builder) {
        this.path=builder.path;
        this.fileName=builder.nameFile;
        this.directory=builder.mkdir;
        if(!directory.isEmpty()){
            File direcAux=new File(this.path+this.directory);
            if(!direcAux.exists())System.out.println(direcAux.mkdir());
            this.directory+="\\";
        }
        this.file =new File(path+directory+fileName);
    }



    public void writeLoggerFile(Throwable throwable) {
        try(FileOutputStream fileOutputStream=new FileOutputStream(file.getAbsolutePath(),true);
            PrintStream printStream=new PrintStream(fileOutputStream)){
            printStream.print("Date:"+ LocalDate.now()+" ");
            throwable.printStackTrace(printStream);
            printStream.flush();
        }catch (IOException e){
            System.out.println("Ha ocurrido un error"+e.getMessage());
        }

    }

    public void fileInfo() throws IOException {
        System.out.println(file.getCanonicalPath());

    }
    public static class Builder{
        private String path;
        private String nameFile;
        private String mkdir;

        public Logger.Builder setPath(String path){
            Objects.requireNonNull(path);
            this.path=path;
            return this;

        }
        public Logger.Builder setName(String nameFile){
            Objects.requireNonNull(nameFile);
            this.nameFile=nameFile;
            return this;
        }
        public Logger.Builder setMkdir(String mkdir){
            Objects.requireNonNull(mkdir);
            this.mkdir=mkdir;
            return this;
        }
        public Logger build(){
            return new Logger(this);
        }

    }
}
