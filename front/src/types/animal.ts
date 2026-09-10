export enum TipoAnimal {
    CACHORRO = 'CACHORRO',
    GATO = 'GATO',
    PASSARO = 'PASSARO',
    PORCO = 'PORCO',
    CAVALO = 'CAVALO',
    VACA = 'VACA',
    PORCO_DA_INDIA = 'PORCO_DA_INDIA'  
}

export interface Animal {
    registro: number
    name: string
    image: string
    tipoAnimal: TipoAnimal
    raca: string
    peso: number
    cor: string
    idade: number
    donoId: number
    descricao: string
    endereco: string
}