import { Component, OnInit } from '@angular/core';
import {copy} from "clipboard";
import {Etudiant} from "../../_model/Etudiant";
import {EtudiantService} from "../../_service/etudiant-service.service";

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.scss']
})
export class EtudiantComponent implements OnInit {
  public copy: string;
  etudiants: Etudiant[] = [];
  newEtudiant: Etudiant = new Etudiant();
  constructor(private etudiantService: EtudiantService) { }

  ngOnInit(): void {
    this.loadEtudiants();
  }

  loadEtudiants(): void {
    this.etudiantService.getAllEtudiants().subscribe(data => {
      this.etudiants = data;
    });
  }
  restartPage() {
    window.location.reload();
  }
  onSubmit(): void {
    this.etudiantService.addEtudiant(this.newEtudiant).subscribe(
      (addedEtudiant) => {
        console.log('Student added successfully:', addedEtudiant);
        alert('Student added successfully');
        this.newEtudiant = new Etudiant();
        this.restartPage();
      },
      (error) => {
        console.error('Error adding student:', error);
      }
    );
  }
}
