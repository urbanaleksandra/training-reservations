import { Training } from './Training';

export class TrainingDay{
    id : number;
    day : string;
    training : Training = new Training();
    trainer : string;
    startsAt : string;
}