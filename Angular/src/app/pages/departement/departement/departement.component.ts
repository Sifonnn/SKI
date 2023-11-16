import { Component, OnInit } from '@angular/core';
import {Departement} from "../../../_model/Departement";
import {DepartementServiceService} from "../../../_service/departement-service.service";

@Component({
  selector: 'app-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.scss']
})
export class DepartementComponent implements OnInit {

  public copy: string;
  departements: Departement[] = [];
  newDepartement: Departement = new Departement();

  constructor(private departementServiceService: DepartementServiceService) { }

  ngOnInit(): void {
    this.loadDepartements();
  }

  loadDepartements(): void {
    this.departementServiceService.getAllDepartements().subscribe(data => {
      this.departements = data;
    });
  }
  restartPage() {
    window.location.reload();
  }

  onSubmit(): void {
    this.departementServiceService.addDepartement(this.newDepartement).subscribe(
      (addedDepartement) => {
        console.log('Student added successfully:', addedDepartement);
        alert('Departement added successfully');
        this.newDepartement = new Departement();
        this.restartPage();
      },
      (error) => {
        console.error('Error adding Departement:', error);
      }
    );
  }

}
