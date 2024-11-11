package U4.src.view;

import U4.src.control.ConcreteUserStory;
import U4.src.control.Container;
import U4.src.control.UserStory;
import U4.src.control.exceptions.ContainerException;
import U4.src.persistence.exceptions.PersistenceException;

import java.util.InputMismatchException;
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
        String[] command = new String[]{""};
        while(!command[0].equals("exit")) {
            System.out.println("Geben Sie Ihr Befehl ein:");
            System.out.print("> ");
            command = sc.nextLine().split(" ");
            switch (command[0]) {
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
                    this.commandDump(con,command);
                    break;
                case "exit":
                    break;
                case "help":
                    this.commandHelp();
                    break;
                default:
                    System.out.println("Der Befehl \"" + command[0] + "\" wurde nicht erkannt. Nutze \"help\" für Hilfe!");
            }
        }
    }

    private void commandEnter(Container con,Scanner sc){
        String title = this.readLine("Wie soll Ihre User Story heißen?",sc);
        Integer ID = this.readLine("Welche ID soll Ihre User Story haben?",sc,0,Integer.MAX_VALUE);
        sc.nextLine();
        String acceptanceCriteria = this.readLine("Welches Akzeptanzkriterium soll Ihre User Story haben?",sc);
        int relValue = this.readLine("Welchen Relativen Mehrwert hat Ihre User Story? (1-5)",sc,1,5);
        int relPenalty = this.readLine("Welche Relative Strafe hat Ihre User Story? (1-5)",sc,1,5);
        int relRisk = this.readLine("Welches Relative Risiko hat Ihre User Story? (1-5)",sc,1,5);
        int expense = this.readLine("Welcher Aufwand hat Ihre User Story?",sc,0,Integer.MAX_VALUE);
        sc.nextLine();
        String project = this.readLine("Zu welchem Projekt gehört Ihre User Story?",sc);

        try{
            con.addUserStory(new ConcreteUserStory(ID,title,relValue,relPenalty,relRisk,expense,acceptanceCriteria,project));
        } catch (ContainerException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    private int readLine(String message,Scanner sc, int min, int max){
        System.out.println(message);
        try{
            int result = sc.nextInt();
            if(result<min || result>max){
                System.out.println("Bitte geben Sie einen Wert zwischen "+min+" und "+max+" ein!");
                return this.readLine(message,sc,min,max);
            }
            return result;
        } catch (InputMismatchException e){
            System.out.println("Sie müssen eine gültige Zahl als Eingabe wählen!");
            sc.nextLine();
            return this.readLine(message,sc,min,max);
        } catch (Exception e){
            System.out.println("Es ist ein Fehler aufgetreten: "+e.getMessage());
            sc.nextLine();
            return this.readLine(message,sc,min,max);
        }
    }
    private String readLine(String message,Scanner sc){
        System.out.println(message);
        try{
            return sc.nextLine();
        }catch(Exception e){
            System.out.println("Es ist ein Fehler aufgetreten: "+e.getMessage());
            sc.nextLine();
            return this.readLine(message,sc);
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

    private void commandDump(Container con, String[] command){
        if(command.length>1 && command[1].equals("projekt")){
            if(command.length==2){
                System.out.println("Der angegebene Befehl ist unvollständig! Sie müssen das Projekt spezifizieren!");
            }else{
                UserStoryView.dump(con.getCurrentList(),command[2]);
            }
        }else{
            UserStoryView.dump(con.getCurrentList(),"");
        }

    }

    private void commandHelp(){
        System.out.println(
                "Verfügbare Befehle:\n" +
                "enter:\tFügt eine neue User Story hinzu\n" +
                "store:\tSpeichert alle User Stories in den Speicher\n" +
                "load:\tLädt alle User Stories aus dem Speicher\n" +
                "dump( projekt PROJEKTNAME):\tGibt alle User Stories aus. Ist kein Parameter angegeben, erfolgt die Ausgabe nach Priorisierung, sonst nach Projekt\n" +
                "exit:\tBeendet das Programm\n");
    }
}
