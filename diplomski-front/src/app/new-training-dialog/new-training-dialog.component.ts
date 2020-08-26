import { Component, OnInit, Input, EventEmitter, Output, ViewEncapsulation } from '@angular/core';
import { NgbActiveModal, NgbPopoverConfig } from '@ng-bootstrap/ng-bootstrap';
import { DayAndTime } from '../model/DayAndTime';
import { TrainingService } from '../service/training.service';
import { Training } from '../model/Training';
import { TrainingDay } from '../model/TrainingDay';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-training-dialog',
  templateUrl: './new-training-dialog.component.html',
  styleUrls: ['./new-training-dialog.component.css'],
  encapsulation: ViewEncapsulation.None,
  styles: [`
    .popoverClass {
      background: #F5992E;
      font-size: 125%;
      color: black;
    }
    .popoverClass popoverTitle {
      color: black;
    }
  `]
})
export class NewTrainingDialogComponent implements OnInit {

  time = {hour: 12, minute: 30};
  daysAndTimes: DayAndTime[] = [];
  dayOption: string = "MONDAY";
  description: string;
  capacity: number;
  duration: number;
  trainingName: string;
  trainerName: string;
  @Input() public mode: string;
  @Input() public trainingToUpdate: Training;
  @Input() public trainingID: number;
  @Input() public trainingDays: TrainingDay[];
  @Input() public trainerNameInput: string;
  @Output() updatedTraining: EventEmitter<boolean> = new EventEmitter();
  @Input() selectedTrainingDay: TrainingDay = null;
  backup: TrainingDay = null;
  validateForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private trainingService: TrainingService, private fb: FormBuilder, private config: NgbPopoverConfig) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      trainingName: ['', [Validators.required, Validators.pattern('[A-Za-z0-9 ]+')]],
      trainerName: ['',[Validators.required, Validators.pattern('[A-Za-z ]+')]],
      duration: [null, [Validators.required, Validators.pattern('[0-9]+')]],
      capacity: [null, [Validators.required, Validators.pattern('[0-9]+')]],
      description: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9,.!? ]*')]],
    })
    this.backup = new TrainingDay();
    this.backup = this.selectedTrainingDay;
    if(this.mode === 'update'){
      this.setupData(this.trainingToUpdate);
    }
  }

  setupData(training: Training): void {
    this.validateForm.controls['trainingName'].setValue(training.name);
    this.validateForm.controls['description'].setValue(training.description);
    this.validateForm.controls['duration'].setValue(training.duration);
    this.validateForm.controls['capacity'].setValue(training.capacity);
    this.validateForm.controls['trainerName'].setValue(this.trainerNameInput);
    this.trainingID = training.id;
    this.dayOption = this.selectedTrainingDay.day.toUpperCase();
    this.time['hour'] = parseInt(this.selectedTrainingDay.startsAt.split(":")[0]);
    this.time['minute'] = parseInt(this.selectedTrainingDay.startsAt.split(":")[1]);

    if(this.trainingDays.length > 0){
      this.trainingDays.forEach(trainingDay => {
        this.daysAndTimes.push(new DayAndTime(trainingDay.id, trainingDay.day, trainingDay.startsAt))
      })
    }
  }

  addDateAndTime(): void {
    let hour = '';
    let minutes = '';
    if(this.time['hour'] < 10){
      hour = `0${this.time['hour']}`;
    }else {
      hour = `${this.time['hour']}`;
    }

    if(this.time['minute'] < 10){
      minutes = `0${this.time['minute']}`;
    }else {
      minutes = `${this.time['minute']}`;
    }

    let time = `${hour}:${minutes}`;
    if(this.selectedTrainingDay !== null){
      
      for(let trainingDay of this.trainingDays){
        if(trainingDay.id === this.selectedTrainingDay.id){
          let dayAndTime: DayAndTime = this.daysAndTimes.filter(x => x.id === this.selectedTrainingDay.id)[0];
          dayAndTime.day = this.dayOption;
          dayAndTime.time = time;
          break;
        }
      }

    }else {
      let dat: DayAndTime = new DayAndTime(null, this.dayOption, time);
      if(this.daysAndTimes.indexOf(dat) === -1){

        this.daysAndTimes.push(dat);
      }
    }
  }

  removeDayAndTime(dayAndTime: DayAndTime): void {
    this.daysAndTimes.splice(this.daysAndTimes.indexOf(dayAndTime), 1);
  }

  submitTraining(): void {
    if(this.validateForm.valid){
      let training: Training = new Training();
      training.capacity = this.validateForm.value['capacity'];
      training.description = this.validateForm.value['description'];
      training.name = this.validateForm.value['trainingName'];
      training.duration = this.validateForm.value['duration'];

      if(this.mode === 'update'){
        training.id = this.trainingID;
      }
      
      let body = {
        training: training,
        trainer: this.validateForm.value['trainerName'],
        daysOfTraining: this.daysAndTimes
      }

      if(this.mode === 'update'){
        this.trainingService.updateTraining(body).subscribe(data => {
          this.updatedTraining.emit(true);
          this.activeModal.close();
        });
      }else {
        this.trainingService.createNewTraining(body).subscribe(data => {
          this.updatedTraining.emit(true);
          this.activeModal.close();
        });
      }
    }else {
      alert("Some of the inputs are not valid. ");
    }
  }

  newDay(): void {
    this.selectedTrainingDay = null;
    this.addDateAndTime();
    this.selectedTrainingDay = this.backup;
  }
}