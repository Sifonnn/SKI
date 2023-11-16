import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Universite} from "../_model/Universite";

@Injectable({
  providedIn: 'root'
})
export class UniversiteService {

  private baseUrl = 'http://localhost:8089/Kaddem/universite';

  constructor(private http: HttpClient) { }
  getAllUniversites(): Observable<Universite[]> {
    return this.http.get<Universite[]>(`${this.baseUrl}/retrieve-all-universites`);
  }
  addUniversite(universite: Universite): Observable<Universite> {
    return this.http.post<Universite>(`${this.baseUrl}/add-universite`, universite);
  }
}
