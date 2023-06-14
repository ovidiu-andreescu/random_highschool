energ = '1s-2s-2p-3s-3p-4s-3d-4p-5s-4d-5p-6s-4f-5d-6p-7s-5f-6d-7p'
strat_energ = tuple(energ.split('-'))
bloc = None
Grupa = None
Perioada = None

s_max = 2
p_max = 6
d_max = 10
f_max = 14

elem = input('Introdu elementul: ')
numar_elect = int(input(f'Introdu numarul de elctroni al elementului {elem}: '))
index = 0
conf_elect = []

def suma(lista):
    suma = 0
    if len(lista) > 0:
        for i in lista:
            suma += i[1]
    return suma

def bloc(lista):
    if 's' in conf_elect[len(conf_elect) - 1][0]:
        bloc = 's'
        grupa = 14

def prin_conf(lista):
    for i in lista:
        print(i[0],'^',i[1], end=" ")
        
        
while suma(conf_elect) < numar_elect:
    if 's' in strat_energ[index]:
        if numar_elect - suma(conf_elect) < s_max:
            conf_elect.append((strat_energ[index], numar_elect - suma(conf_elect)))
        else:
            conf_elect.append((strat_energ[index], s_max))
    elif 'p' in strat_energ[index]:
        if numar_elect - suma(conf_elect) < p_max:
            conf_elect.append((strat_energ[index], numar_elect - suma(conf_elect)))
        else:
            conf_elect.append((strat_energ[index], p_max))
    elif 'd' in strat_energ[index]:
        if numar_elect - suma(conf_elect) < d_max:
            conf_elect.append((strat_energ[index], numar_elect - suma(conf_elect)))
        else:
            conf_elect.append((strat_energ[index], d_max))
    elif 'f' in strat_energ[index]:
        if numar_elect - suma(conf_elect) < f_max:
            conf_elect.append((strat_energ[index], numar_elect - suma(conf_elect)))
        else:
            conf_elect.append((strat_energ[index], f_max))
    
    index += 1

prin_conf(conf_elect)
