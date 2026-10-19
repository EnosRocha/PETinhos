export enum TipoAnimal {
    CACHORRO = 'CACHORRO',
    GATO = 'GATO',
    PASSARO = 'PASSARO',
    PORCO = 'PORCO',
    CAVALO = 'CAVALO',
    VACA = 'VACA',
    PORCO_DA_INDIA = 'PORCO_DA_INDIA'
}

export interface AnimalImage {
    id: number
    url: string
    ordem: number
}

export interface Animal {
    registro: number
    name: string
    image: AnimalImage[]
    tipoAnimal: TipoAnimal
    raca: string
    peso: number
    cor: string
    idade: number
    donoId: number
    tutorId: string | null
    descricao: string
    endereco: string
    statusAnimal: StatusAnimal
}

export enum StatusAnimal {
    DISPONIVEL = 'DISPONÍVEL',
    ADOTADO = 'ADOTADO'
}