# read a text file, replace multiple words specified in a dictionary
# write the modified text back to a file
 
import re
import os
 
def replace_words(text, word_dic):
    """
    take a text and replace words that match a key in a dictionary with
    the associated value, return the changed text
    """
    rc = re.compile('|'.join(map(re.escape, word_dic)))
    def translate(match):
        return word_dic[match.group(0)]
    return rc.sub(translate, text)
 
def list_files(path):
    # returns a list of names (with extension, without full path) of all files 
    # in folder path
    files = []
    for name in os.listdir(path):
        if os.path.isfile(os.path.join(path, name)):
            files.append(name)
    return files 
 

 
def replace_file(path,word_dic):
    # read the file
    print(path)
    fin = open(path, "r")
    str2 = fin.read()
    fin.close()
     
    # the dictionary has target_word:replacement_word pairs
    
 
    # call the function and get the changed text
    str3 = replace_words(str2, word_dic)
 
    # test
    
 
    # write changed text back out
    fout = open(path, "w")
    fout.write(str3)
    fout.close()


def convertir(word):
    if word[0:3]=='get' or word[0:3]=='set':
        return word[0:3]+word[5].upper()+word[6:len(word)]
    else:
        return word[2:5]+'_'+word[5:len(word)].lower()




word_dic = [
        'xesisCodigo',
        'xesisDescripcion',
        'getXesisCodigo',
        'setXesisCodigo',
        'getXesisDescripcion',
        'setXesisDescripcion',
        'sedfaCodigo',
        'sedfaCantidad',
        'setsvCodigo',
        'sefacCodigo',
        'getSedfaCodigo',
        'setSedfaCodigo',
        'getSedfaCantidad',
        'setSedfaCantidad',
        'getSetsvCodigo',
        'setSetsvCodigo',
        'getSefacCodigo',
        'setSefacCodigo',
        'peempCodigo',
        'peempNombres',
        'peempApellidos',
        'peempCedula',
        'peempPasaporte',
        'getPeempCodigo',
        'setPeempCodigo',
        'getPeempNombres',
        'setPeempNombres',
        'getPeempApellidos',
        'setPeempApellidos',
        'getPeempCedula',
        'setPeempCedula',
        'getPeempPasaporte',
        'setPeempPasaporte',
        'xeopcDescipcion',
        'xeopcCodigo',
        'getXeopcCodigo',
        'setXeopcCodigo',
        'getSisCodigo',
        'setSisCodigo',
        'xeoppFechaAsignacion',
        'xeoppActivo',
        'getXeoppFechaAsignacion',
        'setXeoppFechaAsignacion',
        'getXeoppActivo',
        'setXeoppActivo',
        'xeperCodigo',
        'xeopcCodigo',
        'getXeperCodigo',
        'setXeperCodigo',
        'getXeopcCodigo',
        'setXeopcCodigo',
        'getSisCodigo',
        'setSisCodigo',
        'sepacCodigo',
        'sepacNombre',
        'sepacAreaGeografica',
        'sepacSeguro',
        'getSepacCodigo',
        'setSepacCodigo',
        'getSepacNombre',
        'setSepacNombre',
        'getSepacAreaGeografica',
        'setSepacAreaGeografica',
        'getSepacSeguro',
        'setSepacSeguro',
        'xeperCodigo',
        'xeperDescripcion',
        'getXeperCodigo',
        'setXeperCodigo',
        'getXeperDescripcion',
        'setXeperDescripcion',
        'seproCodigo',
        'seproNombre',
        'seproPorcentajeDeducible',
        'seproAreaGeografica',
        'getSeproCodigo',
        'setSeproCodigo',
        'getSeproNombre',
        'setSeproNombre',
        'getSeproPorcentajeDeducible',
        'setSeproPorcentajeDeducible',
        'getSeproAreaGeografica',
        'setSeproAreaGeografica',
        'rolInsert',
        'rolUpdate',
        'rolDelete',
        'rolSelect',
        'getRolInsert',
        'setRolInsert',
        'getRolUpdate',
        'setRolUpdate',
        'getRolDelete',
        'setRolDelete',
        'getRolSelect',
        'setRolSelect',
        'setsvCodigo',
        'setsvNombre',
        'setsvCosto',
        'setsvDescripcion',
        'seproCodigo',
        'getSetsvCodigo',
        'setSetsvCodigo',
        'getSetsvNombre',
        'setSetsvNombre',
        'getSetsvCosto',
        'setSetsvCosto',
        'getSetsvDescripcion',
        'setSetsvDescripcion',
        'getSeproCodigo',
        'setSeproCodigo',
        'xeusuCodigo',
        'xeusuId',
        'xeusuPassword',
        'peempCodigo',
        'xeperCodigo',
        'getXeusuCodigo',
        'setXeusuCodigo',
        'getXeusuId',
        'setXeusuId',
        'getXeusuPassword',
        'setXeusuPassword',
        'getPeempCodigo',
        'setPeempCodigo',
        'getXeperCodigo',
        'setXeperCodigo']

wd={}
for word in word_dic:
    wd[word]=convertir(word)

for file in list_files("src\java\Modelo"):
    replace_file("src\java\Modelo"+"\\"+file,wd)
