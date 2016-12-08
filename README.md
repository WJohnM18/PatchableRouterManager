Patchable Router Manager Description
==============================

Project Description:
    A Command Line application that will take in a CSV file of router data and
    display the routers that can be patched, which is determined by the 
    following criteria:
        - The router has not already been patched
        - The current version of the router OS is 12 or above
        - There are no other routers which share the same IP address
        - There are no other routers which share the same hostname


How to Run:
    - Navigate to src/ folder
    - Compile Main.java by running the command:
                      'javac Main.java'

    - Execute by running the command:
                      'java Main.java <path of the csv file>'
