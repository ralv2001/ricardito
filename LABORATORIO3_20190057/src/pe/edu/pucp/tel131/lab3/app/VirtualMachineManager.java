package pe.edu.pucp.tel131.lab3.app;

import pe.edu.pucp.tel131.lab3.bean.OperatingSystemBean;
import pe.edu.pucp.tel131.lab3.bean.VirtualMachineBean;
import pe.edu.pucp.tel131.lab3.exception.*;
import pe.edu.pucp.tel131.lab3.util.CommandLineTable;
import pe.edu.pucp.tel131.lab3.util.Lab3Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pe.edu.pucp.tel131.lab3.util.Lab3Utils.IPV4_CIDR_REGEX;

public class VirtualMachineManager {

    private final Scanner scanner;
    private VirtualMachineBean vm = new VirtualMachineBean();

    private List<VirtualMachineBean> virtualMachineBeans = new ArrayList<>();

    /* NO MODIFICAR */
    public VirtualMachineManager(Scanner scanner) {
        this.scanner = scanner;
    }

    // TODO: Completar
    public void createVirtualMachineName() {
        System.out.print("Ingrese nombre de la VM: ");
        try{
            String name = scanner.nextLine();

            for(VirtualMachineBean e: virtualMachineBeans){
                if(e.getName().equals(name)){
                    throw new NameAlreadyExistsException();
                }
            }
            vm.setName(name);
            virtualMachineBeans.add(vm);
        }
        catch(NameAlreadyExistsException e){
            System.err.println(e.getMessage());
        }

    }

    // TODO: Completar
    public void configureVirtualMachine() {
        System.out.println("\n############ Configura tu VM ############");

        // Elegir número de cores
        while (true) {
            try {
                System.out.print("Ingrese número de cores: ");
                int cores = Integer.parseInt(scanner.nextLine());
                if (cores % 2 == 1) {
                    throw new InvalidCoresException();
                }
                vm.setCores(cores);

                System.out.print("Ingrese la ram (GBs): ");
                int ram = Integer.parseInt(scanner.nextLine());
                if (ram % 4 == 1) {
                    throw new InvalidCoresException();
                }
                vm.setRam(ram);

                System.out.print("Elija el volumen del disco (GBs): ");
                int gigas = Integer.parseInt(scanner.nextLine());
                vm.setDisk(gigas);

                System.out.println("Elija una dirección IP");
                String ip = scanner.nextLine();
                if(!ip.matches(IPV4_CIDR_REGEX)){
                    throw new MalIpException();
                }
                vm.setIp(ip);
                break;
            }
            catch (InvalidCoresException ex) {
                System.err.println(ex.getMessage());
            }
            catch (NumberFormatException ex) {
                System.err.println("Ingrese un número entero");
            }
            catch(MalIpException ex){
                System.err.println(ex.getMessage());
            }
            finally {
                System.out.println();
            }
        }

        // completar ...

        System.out.println("\n############ VM Configurada! ############");
    }

    // TODO: Completar
    public void chooseOperatingSystem() {

        // Elige el tipo de sistema operativo
        while (true) {
            for (int i = 0; i < OperatingSystemBean.availableTypes.length; i++) {
                System.out.printf("%d. %s\n", i, OperatingSystemBean.availableTypes[i]);
            }
            System.out.print("Elija una opción de tipo de OS: ");
            try {
                String osType = OperatingSystemBean.availableTypes[Integer.parseInt(scanner.nextLine())];
                vm.getOperatingSystem().setType(osType);
                break;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("Ingrese un valor de la lista");
            } catch (NumberFormatException ex) {
                System.err.println("Ingrese un número entero");
            } finally {
                System.out.println();
            }
        }

        // Completar...

    }

    /* NO MODIFICAR */
    public void printVirtualMachine() {
        CommandLineTable ct = new CommandLineTable();
        ct.setShowVerticalLines(true);
        ct.setHeaders("name", "cores", "ram (GBs)", "disk (GBs)", "ip", "OS type", "OS distro");
        ct.addRow(vm.getName(),
                String.valueOf(vm.getCores()),
                String.valueOf(vm.getRam()),
                String.valueOf(vm.getDisk()),
                vm.getIp(),
                String.valueOf(vm.getOperatingSystem().getType()),
                String.valueOf(vm.getOperatingSystem().getDistro()));
        ct.print();
    }

    // TODO: completar
    public void deployVirtualMachine() {
        // completar validación

        /* Descomentar las 3 siguientes líneas cuando esté completa la validación: */
        //virtualMachineBeans.add(vm);
        //vm = new VirtualMachineBean();
        //Lab3Utils.launchVirtualMachine();
    }

    /* NO MODIFICAR */
    public void listVirtualMachines() {
        System.out.println("######################## Virtual Machines ########################");
        CommandLineTable ct = new CommandLineTable();
        ct.setShowVerticalLines(true);
        ct.setHeaders("name", "cores", "ram (GBs)", "disk (GBs)", "ip", "OS type", "OS distro");
        for (VirtualMachineBean vm: virtualMachineBeans) {
            ct.addRow(vm.getName(),
                    String.valueOf(vm.getCores()),
                    String.valueOf(vm.getRam()),
                    String.valueOf(vm.getDisk()),
                    vm.getIp(),
                    String.valueOf(vm.getOperatingSystem().getType()),
                    String.valueOf(vm.getOperatingSystem().getDistro()));
        }
        ct.print();
    }

    /* NO MODIFICAR */
    public void exit() {
        System.out.println("\nbye!\n");
    }

    /* NO MODIFICAR */
    public void setVirtualMachineBeans(List<VirtualMachineBean> virtualMachineBeans) {
        this.virtualMachineBeans = virtualMachineBeans;
    }

    /* NO MODIFICAR */
    public List<VirtualMachineBean> getVirtualMachineBeans() {
        return virtualMachineBeans;
    }
}
