import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EndpointsService {

  constructor(private http: HttpClient) { }

  saveSupermarket(supermarketObj: any, file: any) {
    let formData = new FormData();
    formData.append('file', file);
    formData.append('supermarketObj', JSON.stringify( supermarketObj ) );

    return this.http.post(`${environment.END_POINTS}/saveSupermarket`, formData);
  }

  deleteSupermarket(id: number) {
    return this.http.delete(`${environment.END_POINTS}/deleteSupermarket/${id}`);
  }


  getSupermarketList() {
    return this.http.get(`${environment.END_POINTS}/getSupermarketList`);
  }

  updateSupermarket(supermarketObj: any, file: any) {
    
    let formData = new FormData();
    formData.append('file', file);
    formData.append('supermarketObj', JSON.stringify( supermarketObj ) );

    return this.http.post(`${environment.END_POINTS}/updateSupermarket`, formData);
  }
}
