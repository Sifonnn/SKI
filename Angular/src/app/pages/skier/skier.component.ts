import { Component, OnInit } from '@angular/core';
import {Etudiant} from "../../_model/Etudiant";
import {EtudiantService} from "../../_service/etudiant-service.service";
import {Skier} from "../../_model/Skier";
import {SkierService} from "../../_service/skier.service";

@Component({
  selector: 'app-skier',
  templateUrl: './skier.component.html',
  styleUrls: ['./skier.component.scss']
})
export class SkierComponent implements OnInit {

  public copy: string;
  skiers: Skier[] = [];
  newSkier: Skier = new Skier();
  constructor(private skierService: SkierService) { }

  ngOnInit(): void {
    this.loadaddednewSkiers();
  }

  loadaddednewSkiers(): void {
    this.skierService.getAllSkiers().subscribe(data => {
      this.skiers = data;
    });
  }
  restartPage() {
    window.location.reload();
  }
  onSubmit(): void {
    this.skierService.addSkier(this.newSkier).subscribe(
      (addednewSkier) => {
        console.log('newSkier added successfully:', addednewSkier);
        alert('Skier added successfully');
        this.newSkier = new Skier();
        this.restartPage();
      },
      (error) => {
        console.error('Error adding student:', error);
      }
    );
  }

}
