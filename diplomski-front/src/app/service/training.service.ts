import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WeekTraining } from '../model/WeekTraining';
import { TrainingDay } from '../model/TrainingDay';
import { environment } from 'src/environments/environment';

const baseURI = environment.baseURI;

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }
  
  getAllTrainings() : Observable<WeekTraining[]>{
    return this.http.get<WeekTraining[]>(baseURI + 'training-day');
  }

  scheduleTraining(body : any){
    return this.http.post(baseURI + 'reserved-training', body);
  }

  getNumberOfScheduled(id : number, date : string){
    return this.http.get<any>(baseURI + `reserved-training/${id}/${date}`);
  }

  confirmArrival(body : any){
    return this.http.post(baseURI + 'reserved-training/confirm-arrival', body);
  }

  createNewTraining(body: any): Observable<any> {
    return this.http.post(baseURI + 'training', body);
  }

  updateTraining(body: any): Observable<any> {
    return this.http.put(baseURI + `training/${body['training'].id}`, body);
  }

  getAllByTraining(id: number): Observable<any> {
    return this.http.get(baseURI + `training-day/training/${id}`);
  }

}
