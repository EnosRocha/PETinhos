import axios from "axios";
import { useAuthStore } from '@/store/storeAuth'

export async function buscarPetsDisponiveis() {
    const auth = useAuthStore()
    const response = await axios.get('http://localhost:8080/animals', {
        headers: { Authorization: `Bearer ${auth.token}` }
    });
    return response.data;
}

export async function cadastrarTutor(data: {
    name: string,
    password: string,
    phone: string,
    birthday: string,
    email: string,
}) {
    const response = await axios.post('http://localhost:8080/tutor', data)
    return response.data
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
    const auth = useAuthStore()
    const data = new FormData();
    data.append('animal', new Blob([JSON.stringify(animal)], { type: 'application/json' }));
    files.forEach(f => data.append('images', f));

    const response = await axios.post('http://localhost:8080/animals', data, {
        headers: { 
            'Authorization': `Bearer ${auth.token}`,
            'Content-Type': 'multipart/form-data'
        }
    });
    return response.data;
}


export async function login(email: string, password: string) {
    const response = await axios.post('http://localhost:8080/auth/login', { email, password })
    return response.data
}