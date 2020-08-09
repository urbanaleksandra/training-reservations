import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WeekTraining } from '../model/WeekTraining';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }

  getAllTrainings() : Observable<WeekTraining[]>{
    return this.http.get<WeekTraining[]>('http://localhost:9000/training-day');
  }
}
