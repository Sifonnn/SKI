import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Departement} from "../_model/Departement";

@Injectable({
  providedIn: 'root'
})
export class DepartementServiceService {

  private baseUrl = 'http://localhost:8089/Kaddem/departement';

   constructor(private http: HttpClient) { }

  getAllDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.baseUrl}/retrieve-all-departements`);
  }
  addDepartement(departement: Departement): Observable<Departement> {
    return this.http.post<Departement>(`${this.baseUrl}/add-departement`, departement);
  }
}
