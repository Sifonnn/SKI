import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contrat} from "../_model/Contrat";

@Injectable({
  providedIn: 'root'
})
export class ContratServiceService {

  private baseUrl = 'http://localhost:8089/Kaddem/contrat';
  constructor(private http: HttpClient) { }

  getAllContrats(): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(`${this.baseUrl}/retrieve-all-contrats`);
  }
  addContrat(contart: Contrat): Observable<Contrat> {
    return this.http.post<Contrat>(`${this.baseUrl}/add-contrat`, contart);
  }
}
