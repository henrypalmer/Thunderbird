
class ThunderbirdContact extends HttpRequest implements Runnable {
    private String firstName;
    public String getFirstName() { return Integer.toString(seatLocation) + " " + firstName; }

    private String lastName;
    public String getLastName() {return lastName; }

    private String preferredName;
    public String getPreferredName() {return preferredName; }

    private String email;
    public String getEmail() {return email;}
    
    private int seatLocation; 
    public int getSeat() { return (seatLocation-1); }

    static boolean emailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    ThunderbirdContact(String urlIn) {
        super(urlIn);

        firstName = "";
        lastName = "";
        preferredName = "";
        email = "";
        seatLocation = 0;

        // Todo: Add additional fields. 
        // HRP: IMPLEMENTED
    }

    public Boolean Load() {
        Boolean returnValue = false;
        System.out.println("Loading: " + requestURL);
        if (super.readURL()) {
            Parse(); 
            returnValue = true;
        }

        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            // Todo: Parse for additional fields. 
            // HRP: IMPLEMENTED

            if (subString.length > 3) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("preferredName")) {
                    preferredName = subString[3];
                }
                if (subString[1].equals("email")) {
                    email = subString[3];
                }
                if (subString[1].equals("seatLocation")) {
                    try {
                        seatLocation = 0; 
                        if (!subString[3].equals("")) {
                            seatLocation = Integer.parseInt(subString[3]);
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }    
    }

    public void Validate() {
        if (urlContent.size() < 1) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: No content loaded\n");
            return; // Returning from the middle of a method is controversial.
        }

        // Todo: Add author's name and email address to failed messages. 
        if (firstName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: First Name (\"firstName\") required but not found\n\n");
            System.out.println(this);
        } else if (lastName.length() == 0) {   
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);
        } else if (preferredName.length() > 16 || preferredName.length() < 2) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: Preferred name must be longer than 2 characters but shorter than 16.\n\n");
            System.out.println(this);
        } else if (!emailValid(email)) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: Email is invalid.\n\n"); 
            System.out.println(this);
        } else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        }
    }

    public String toString() {

        // Todo: Add additional fields to returnString. 
        // HRP: IMPLEMENTED

        String returnString = "firstName: " + firstName + "\n";
        returnString = returnString + "lastName: " + lastName + "\n";
        returnString = returnString + "preferredName: " + preferredName + "\n";
        returnString = returnString + "email: " + email + "\n";
        returnString = returnString + "seatNumber: " + seatLocation + "\n";
        
        returnString = returnString + super.toString();

        return returnString;
    }

    public void run() {
        Load();
    }
}