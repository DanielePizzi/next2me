import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeyValuePipe } from './pipes/keyValuePipe';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [KeyValuePipe],
  exports: [KeyValuePipe]
})
export class SharedModule { }
