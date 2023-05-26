package pe.edu.pucp.tel131.lab3.app;

 import pe.edu.pucp.tel131.lab3.bean.OperatingSystemBean;
 import pe.edu.pucp.tel131.lab3.bean.VirtualMachineBean;
 import pe.edu.pucp.tel131.lab3.exception.InvalidCoresException;
 import pe.edu.pucp.tel131.lab3.exception.OptionOutOfBoundException;
 import pe.edu.pucp.tel131.lab3.util.Lab3Utils;

 import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final VirtualMachineManager virtualMachineManager;

    /* NO MODIFICAR */
    public Menu() {
        scanner = new Scanner(System.in);
        virtualMachineManager = new VirtualMachineManager(this.scanner);

        VirtualMachineBean virtualMachine =
                new VirtualMachineBean("defaultVM", 2 ,4, 128, "10.0.0.1",
                        new OperatingSystemBean("linux", "arch"));
        virtualMachineManager.getVirtualMachineBeans().add(virtualMachine);
    }

    // TODO: Completar
    public boolean run() {
        displayOptions();

        try {

            int option = Integer.parseInt(scanner.nextLine());
            if(option > 7){
                throw new OptionOutOfBoundException();
            }
            if(option < 0){
                throw new OptionOutOfBoundException();
            }

            switch (option) {
                case 1:
                    // create Virtual Machine
                    virtualMachineManager.createVirtualMachineName();
                    break;
                case 2:
                    virtualMachineManager.configureVirtualMachine();
                    break;
                case 3:
                    virtualMachineManager.chooseOperatingSystem();
                    break;
                case 4:
                    virtualMachineManager.printVirtualMachine();
                    break;
                case 5:
                    virtualMachineManager.deployVirtualMachine();
                    break;
                case 6:
                    virtualMachineManager.listVirtualMachines();
                    break;
                case 7:
                    virtualMachineManager.exit();
                    return false;
            }
        }
        // TODO: Completar 1. Conociendo el menú de despliegue:
        catch(OptionOutOfBoundException e){
            System.err.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            //OptionOutOfBoundException
            System.err.println("Ingrese un número entero");
        }
        return true;
    }

    /* NO MODIFICAR */
    private void displayOptions() {
        System.out.print(Lab3Utils.ANSI_BLUE + "\n ############ Virtual Machine Launcher ############" +
                "\n1. Ingresa nombre de VM" +
                "\n2. Configurar VM" +
                "\n3. Elegir OS" +
                "\n4. Imprimir VM" +
                "\n5. Desplegar VM" +
                "\n6. Listar VMs desplegadas" +
                "\n7. Terminar"+ Lab3Utils.ANSI_RESET +
                "\nElija la acción que desea realizar: ");
    }

}
