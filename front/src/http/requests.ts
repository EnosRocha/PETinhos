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


export async function handleFiles(event: Event) {
    // const input = event.target as HTMLInputElement
    // const files = Array.from(input.files || [])

    // const formData = new FormData()
    // files.forEach(file => formData.append('files', file))

    // await axios.post(`/animals/${petId}/imagens`, formData, {
    //     headers: { 'Content-Type': 'multipart/form-data' }
    // })
}


