import axios from "axios";

export async function buscarPetsDisponiveis() {
    try {
        const response = await axios.get('http://localhost:8080/animals');
        console.log(response.data);

        return response.data;
    } catch (error) {
        console.error('Ocorreu um erro:', error);
    }
}

export interface AnimalForm {
    name: string;
    tipoAnimal: string;
    raca: string;
    peso: number | null;
    cor: string;
    idade: number | null;
    donoId: number;
    descricao: string;
    endereco: string;
}

export async function cadastrarPet(animal: AnimalForm, files: File[]) {
    const data = new FormData();
    data.append('animal', new Blob([JSON.stringify(animal)], { type: 'application/json' }));
    files.forEach(f => data.append('images', f));

    const response = await api.post('/animals', data);
    return response.data;
}

export async function handleFiles(event: Event) {
    // const input = event.target as HTMLInputElement
    // const files = Array.from(input.files || [])

    // const formData = new FormData()
    // files.forEach(file => formData.append('files', file))

    // await axios.post(`/animals/${petId}/imagens`, formData, {
    //     headers: { 'Content-Type': 'multipart/form-data' }
    // })
}


