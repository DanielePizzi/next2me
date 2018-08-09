import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeyValuePipe } from './pipes/keyValuePipe';
import { PasswordStrengthBar } from './components/passwordStrengthBar';
import { NgbdModalContentError } from './components/NgbdModalContentError';
import { OpenClosedFilter } from './pipes/openClosedFilter';
import { DistancePipe } from './pipes/distancePipe';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    KeyValuePipe,
    OpenClosedFilter,
    DistancePipe,
    PasswordStrengthBar,
    NgbdModalContentError,
  ],
  exports: [
    KeyValuePipe,
    OpenClosedFilter,
    DistancePipe,
    PasswordStrengthBar,
    NgbdModalContentError
  ],
})
export class SharedModule { }
