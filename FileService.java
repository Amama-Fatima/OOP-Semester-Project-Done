import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private String filePath;
    private ObjectOutputStream objectOutput;

    public FileService(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
        openObjectOutputStream();
    }

    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    private void openObjectOutputStream(){
        try{
            objectOutput = new ObjectOutputStream(new FileOutputStream(filePath, true));
        } catch (IOException e) {
            System.out.println("IOException occurred while opening object output stream to file: " + filePath);
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return filePath;
    }

    public void setFileName(String filePath) {
        this.filePath = filePath;
    }

    // Updated method to read objects from a serialized file
    public ArrayList<User> readUsersFromFile() {
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    User user = (User) objectInput.readObject();
                    users.add(user);
                    System.out.println("Reading user from file: " + user.toString());
                } catch (EOFException e) {
                    System.out.println("End of file reached");
                    break;
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                    e.printStackTrace();
                }
            }
            System.out.println("Users read from file: " + filePath);
        } catch (IOException e) {
            System.out.println("IOException occurred while reading users from file: " + filePath);
            e.printStackTrace();
        }
        return users;
    }
    
    
    public void writeObjectToFile(User user) throws IOException {
        try{
            objectOutput.writeObject(user);
            System.out.println("Object has been written to the file: " + user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        
        }
    }
    

    // Existing method to update an object in a serialized file
    public void updateUser(User oldUser, User newUser) throws IOException {
        File file = new File(filePath);
        ArrayList<User> users = new ArrayList<>();
        
        try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    User user = (User) objectInput.readObject();
                    if (user.equals(oldUser)) {
                        users.add(newUser);
                    } else {
                        users.add(user);
                    }
                } catch (EOFException e) {
                    System.out.println("End of file reached");
                    break;
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                    e.printStackTrace();
                }
            }
        }
    
        try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(file))) {
            for (User user : users) {
                objectOutput.writeObject(user);
                System.out.println("Writing user to file: " + user.toString());
            }
            System.out.println("Users written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("IOException occurred while writing users to file: " + filePath);
            e.printStackTrace();
        }
    }
    
}




    // // Updated method to write objects to a serialized file
    // public void writeUsersToFile(List<User> users) {
    //     try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(filePath))) {
    //         for (User user : users) {
    //             objectOutput.writeObject(user);
    //             System.out.println("Writing user to file: " + user.toString());
    //         }
    //         System.out.println("Users written to file: " + filePath);
    //     } catch (IOException e) {
    //         System.out.println("IOException occurred while writing users to file: " + filePath);
    //         e.printStackTrace();
    //     }
    // }