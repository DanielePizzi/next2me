export class PointOfInterest {

  private _id: string;
  private _via: string;
  private _nome: string;
  private _distanza: Number;
  private _stato: boolean;
  private _rating: string;

  public get id(): string {
    return this._id;
  }
  public set id(value: string) {
    this._id = value;
  }

  public get via(): string {
    return this._via;
  }
  public set via(value: string) {
    this._via = value;
  }

  public get nome(): string {
    return this._nome;
  }
  public set nome(value: string) {
    this._nome = value;
  }

  public get distanza(): Number {
    return this._distanza;
  }
  public set distanza(value: Number) {
    this._distanza = value;
  }

  public get stato(): boolean {
    return this._stato;
  }
  public set stato(value: boolean) {
    this._stato = value;
  }
  public get rating(): string {
    return this._rating;
  }
  public set rating(value: string) {
    this._rating = value;
  }
}
