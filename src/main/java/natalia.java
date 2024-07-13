public static void natalia(String[] args) {
        //int []arreglo = {10, 20, 30, 40, 50};
        System.out.println("BÚSQUEDA DE UN NÚMERO: ");
        Scanner sc = new Scanner (System.in);
        System.out.println("Cuántos elementos va a tener el arreglo? ");
        int aux = sc.nextInt();
        int []arreglo = new int [aux];
        System.out.println("Digite los elementos: ");
        for (int i = 0; i < aux; i++) {
        arreglo[i] = sc.nextInt();
        }
        System.out.println("¿Qué elemento quiere verificar?");
        int numero = sc.nextInt();
        for (int i = 0; i < arreglo.length; i++) {
        if (arreglo[i] == numero){
        System.out.println("el elemento "+ numero +" está en la posición "+ i);
        }
        if (i == arreglo.length) {
        System.out.println("ese número no existe");
        }
        }


        }
        }