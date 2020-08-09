import { Component, OnInit, Input } from '@angular/core';
import { WeekTraining } from '../model/WeekTraining';
import { TrainingDay } from '../model/TrainingDay';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }

  @Input() weekTraining : WeekTraining;
  trainings : TrainingDay[] = [];
  
  ngOnInit(): void {
    this.trainings = this.weekTraining.allTrainings;
  }

}
