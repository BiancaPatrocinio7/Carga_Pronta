Equipment = { 'Equipment_CPU' : 0.0547, 'Equipment_Monitor_LED' : 0.0384, 'Equipment_Nobreak' : 0.0048, 
           'Equipment_Monitor_CRT' : 0.0839, 'Equipment_Impressora' :  0.1047, 'Equipment_Teclado' : 0.0030,
           'Equipment_Notebook' : 0.0095, 'Equipment_Mouse' : 0.0015

}

Carrier = { 'Carrier_Van' : 8 , 'Carrier_Bongo' : 14,'Carrier_Caminhao' :  40}

Undo_List = {}

def add_undo_list(equipment):
    
    quantity = int(input(f"\nDigite a quantidade que deseja adicionar: "))
    Undo_List[equipment] = quantity
    result = Undo_List[equipment]
    print(Undo_List)
    print('\n')

def calculator_unitary(equipment):   
    quantity = Undo_List[equipment]
    unitary_value = Equipment[equipment]
    cubic_value = float(quantity*unitary_value)
    print("--------------------------------------------------------------------------------------------")
    print(f'Quantidade de volume do {equipment}: {cubic_value:.3f} m³')
    print('\n')

    return cubic_value

def calculator_list():
    volume_total = 0
    print("Listas de Desfazimento: ")
    print(Undo_List)
    print("--------------------------------------------------------------------------------------------")
    for equipment_name in Undo_List:
        volume_total += calculator_unitary(equipment_name)
    print("--------------------------------------------------------------------------------------------")
    print(f"Volume total dos equipamentos da lista: {volume_total:.3f} m³\n")
    return volume_total

def quantity_carrier(volume):
    quantity_van = 0
    quantity_bongo = 0
    quantity_caminhao = 0
    
    while True:
        if volume < Carrier['Carrier_Van'] and volume > 0:
            quantity_van += 1
            #print(f'{volume} < van')
            break
        elif volume >= Carrier['Carrier_Van'] and volume < Carrier['Carrier_Bongo']:
            volume = volume - Carrier['Carrier_Bongo']
            #print(f'{volume} > van')
            quantity_bongo += 1
        elif volume >= Carrier['Carrier_Bongo'] and volume < Carrier['Carrier_Caminhao']:
            volume = volume - Carrier['Carrier_Caminhao']
            #print(f'{volume} > bongo')
            quantity_caminhao += 1
        elif volume >= Carrier['Carrier_Caminhao']:
            volume = volume - Carrier['Carrier_Caminhao']
            #print(f'{volume} > caminhao')
            quantity_caminhao += 1
        else:
            break
    print(f"Quantidade de viagens de Van: {quantity_van}\nQuantidade de viagens de Bongo: {quantity_bongo}\nQuantidade de viagens de Caminhão: {quantity_caminhao}")


def main():
    volume = 205.75
    print(f'Volume total: {volume} m³')
    quantity_carrier(volume)

    '''while True:
        print("Escolha uma opção que deseja adicionar na lista:")
        print("1. CPU")
        print("2. Monitor LED")
        print("3. Monitor CRT")
        print("4. Impressora")
        print("5. Teclado")
        print("6. Notebook")
        print("7. Mouse")
        print("0. Sair")
        print("-1. Calcule")

        option = input("\nDigite o número da opção desejada: ")

        if option == '1':
           add_undo_list('Equipment_CPU')
        elif option == '2':
           add_undo_list('Equipment_Monitor_LED')
        elif option == '3':
           add_undo_list('Equipment_Monitor_CRT')
        elif option == '4':
           add_undo_list('Equipment_Impressora')
        elif option == '5':
           add_undo_list ('Equipment_Teclado')
        elif option == '6':
           add_undo_list('Equipment_Notebook')
        elif option == '7':
           add_undo_list('Equipment_Mouse')
        elif option == '0':
            print("Saindo...")
            break
        elif option == '-1':
            print("Calculando...\n")
            result = calculator_list()
            quantity_carrier(result)
            break
        else:
            print("Opção inválida. Tente novamente.")'''

# Chama a função principal
if __name__ == "__main__":
    main()
