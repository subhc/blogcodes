quote:flip `time`sym`bid`ask!()

upd:{[arg]
 t:arg[0];                   /saves the table name to t
 x:arg[1];                   /saves the data received to x
 z:(count x)#.z.T;           /creates a list with current time (time of receiving x)
 t insert (enlist z),flip x; /insert time and data into t
 }
