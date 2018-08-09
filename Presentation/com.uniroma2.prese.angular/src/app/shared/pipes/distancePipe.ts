import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: 'distancePipe'})
export class DistancePipe implements PipeTransform {
  transform(value, args:string[]) : any {
    if (value >= 1) {
      return value.toFixed(2) + 'km';
    } else {
      return (value*1000).toFixed(2) + ' m';
    }
  }
}
