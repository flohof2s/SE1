package U4.src.view;

import U4.src.control.ConcreteUserStory;
import U4.src.control.Container;
import U4.src.control.UserStory;
import U4.src.control.exceptions.ContainerException;
import U4.src.persistence.exceptions.PersistenceException;

import java.util.Scanner;

public class Client {
    private Container con;
    private Scanner sc;
    public Client(Container con){
        this.con=con;
        this.sc = new Scanner(System.in);
        this.start(this.con,this.sc);
        sc.close();
    }

    private void start(Container con, Scanner sc){
        String command = "";
        while(!command.equals("exit")) {
            System.out.println("Geben Sie Ihr Befehl ein:");
            command = sc.nextLine();
            switch (command) {
                case "enter":
                    this.commandEnter(con,sc);
                    break;
                case "store":
                    this.commandStore(con);
                    break;
                case "load":
                    this.commandLoad(con);
                    break;
                case "dump":
                    this.commandDump(con);
                    break;
                case "exit":
                    break;
                case "help":
                    this.commandHelp();
                    break;
                default:
                    System.out.println("Der Befehl \"" + command + "\" wurde nicht erkannt. Nutze \"help\" für Hilfe!");
            }
        }
    }

    private void commandEnter(Container con,Scanner sc){
        System.out.println("Wie soll Ihre User Story heißen?");
        String title = sc.nextLine();

        System.out.println("Welche ID soll Ihre User Story haben?");
        Integer ID = sc.nextInt();
        sc.nextLine();

        System.out.println("Welches Akzeptanzkriterium soll Ihre User Story haben?");
        String acceptanceCriteria = sc.nextLine();

        System.out.println("Welchen Relativen Mehrwert hat Ihre User Story? (1-5)");
        int relValue = sc.nextInt();
        while(relValue<1 || relValue>5){
            System.out.println("Bitte geben Sie einen Wert zwischen 1 und 5 ein!");
            relValue = sc.nextInt();
        }

        System.out.println("Welche Relative Strafe hat Ihre User Story? (1-5)");
        int relPenalty = sc.nextInt();
        while(relPenalty<1 || relPenalty>5){
            System.out.println("Bitte geben Sie einen Wert zwischen 1 und 5 ein!");
            relPenalty = sc.nextInt();
        }

        System.out.println("Welches Relative Risiko hat Ihre User Story? (1-5)");
        int relRisk = sc.nextInt();
        while(relRisk<1 || relRisk>5){
            System.out.println("Bitte geben Sie einen Wert zwischen 1 und 5 ein!");
            relRisk = sc.nextInt();
        }

        System.out.println("Welcher Aufwand hat Ihre User Story?");
        int expense = sc.nextInt();
        sc.nextLine();
        System.out.println("Zu welchem Projekt gehört Ihre User Story?");
        String project = sc.nextLine();

        try{
            con.addUserStory(new ConcreteUserStory(ID,title,relValue,relPenalty,relRisk,expense,acceptanceCriteria,project));
        } catch (ContainerException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    private void commandStore(Container con){
        try {
            con.store();
        } catch (PersistenceException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    private void commandLoad(Container con){
        try {
            con.load();
        } catch (PersistenceException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    private void commandDump(Container con){
        UserStoryView.dump(con.getCurrentList());
    }

    private void commandHelp(){
        System.out.println(
                "Verfügbare Befehle:\n" +
                "enter:\tFügt eine neue User Story hinzu\n" +
                "store:\tSpeichert alle User Stories in den Speicher\n" +
                "load:\tLädt alle User Stories aus dem Speicher\n" +
                "dump:\tGibt alle User Stories aus\n" +
                "exit:\tBeendet das Programm\n");
    }
}
