import axios from "axios";
import { useAuthStore } from '@/store/storeAuth'

const API_URL = 'http://localhost:8080'

export interface TutorProfile {
    id: string
    name: string
    phone: string | null
    birthday: string | null
    email: string
}

export async function buscarMeuPerfil(): Promise<TutorProfile> {
    const auth = useAuthStore()
    const response = await axios.get<TutorProfile>(`${API_URL}/tutor/me`, {
        headers: { Authorization: `Bearer ${auth.token}` }
    })
    return response.data
}

export async function completarMeuPerfil(data: { phone: string; birthday: string }) {
    const auth = useAuthStore()
    const response = await axios.put(`${API_URL}/tutor/me`, data, {
        headers: { Authorization: `Bearer ${auth.token}` }
    })
    return response.data
}

export async function buscarPetsDisponiveis() {
    const auth = useAuthStore()
    const response = await axios.get(`${API_URL}/animals`, {
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
    const response = await axios.post(`${API_URL}/tutor`, data)
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

    const response = await axios.post(`${API_URL}/animals`, data, {
        headers: { 
            'Authorization': `Bearer ${auth.token}`,
            'Content-Type': 'multipart/form-data'
        }
    });
    return response.data;
}


export async function login(email: string, password: string) {
    const response = await axios.post(`${API_URL}/auth/login`, { email, password })
    return response.data
}

export async function buscarTutor(id: string) {
    const response = await axios.get(`${API_URL}/tutor/${id}`, {
        headers: { Authorization: `Bearer ${useAuthStore().token}` }
    })

    return response.data
}

export async function buscarPet(id: number){
    const response = await axios.get(`${API_URL}/animals/${id}`, {
        headers: { Authorization: `Bearer ${useAuthStore().token}` }
    })
    return response.data
}