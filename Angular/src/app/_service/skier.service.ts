import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contrat} from "../_model/Contrat";
import {Skier} from "../_model/Skier";

@Injectable({
  providedIn: 'root'
})
export class SkierService {

  private baseUrl = 'http://172.20.10.7:8088/api/skier';
  constructor(private http: HttpClient) { }

  getAllSkiers(): Observable<Skier[]> {
    return this.http.get<Skier[]>(`${this.baseUrl}/all`);
  }
  addSkier(skier: Skier): Observable<Skier> {
    return this.http.post<Skier>(`${this.baseUrl}/add`, skier);
  }
}
