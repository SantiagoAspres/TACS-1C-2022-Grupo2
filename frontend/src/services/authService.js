import axios from 'axios';
import Config from '../appConfig.json';

const url = Config.ApiURL;


export async function createUser(data){
    return axios.post(`${url}/accesstoken`, data);
}

//LOCAL STORAGE PROVISIORIO
export async function auth(data){
    return axios.post(`${url}/authorization`, data).then(
        async (response) => {
            if(response.status === 200){
                localStorage.setItem('jwt', response.data.data.token);
                return {
                    status :response.status
                }            
            }else{
                return {
                    status : response.status,
                    message: response.statusText
                }
            }
        }
    );
}
