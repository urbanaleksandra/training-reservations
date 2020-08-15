import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WeekTraining } from '../model/WeekTraining';
import { TrainingDay } from '../model/TrainingDay';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }

  getAllTrainings() : Observable<WeekTraining[]>{
    return this.http.get<WeekTraining[]>('http://localhost:9000/training-day');
  }

  scheduleTraining(body : any){
    return this.http.post('http://localhost:9000/reserved-training', body);
  }

  getNumberOfScheduled(id : number){
    return this.http.get<any>(`http://localhost:9000/reserved-training/${id}`);
  }

  confirmArrival(body : any){
    return this.http.post('http://localhost:9000/reserved-training/confirm-arrival', body);
  }
}
