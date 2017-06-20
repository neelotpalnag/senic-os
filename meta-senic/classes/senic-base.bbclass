def write_template(infile, context, outfile=None):
    template = u''
    # default is to overwrite the template
    # with the rendered version in-place
    if outfile is None:
        outfile = infile
    with open(infile, 'r') as infile_f:
        template = ''.join(infile_f.readlines())
    with open(outfile, 'w') as outfile_f:
        outfile_f.write(template.format(**context))

def debug(value):
    import json
    with open('/tmp/debugx', 'w') as outfile_f:
        outfile_f.write(json.dumps(value))

def datastore(d):
  result = dict()
  for key in d.keys():
    if key.startswith('SNC'):
      result[key] = (d.getVar(key, True))
  return result
  


