#!/usr/bin/env python

# A program ket szoveges fajl osszehasonlitasat vegzi el.

import argparse

goodlines = 0
badlines = 0

def get_arguments():
    parser = argparse.ArgumentParser()
    parser.add_argument("-r", "--resultfile", dest="result", help="The filename of the output of the program -> Result.")
    parser.add_argument("-e", "--expectedfile", dest="expected", help="Filename of the expected result.")
    options = parser.parse_args()
    if not options.result:
        parser .error("[-] Please specify the filename of the result, use --help for more info.")
    elif not options.expected:
        parser .error("[-] Please specify the filename of the expected result, use --help for more info.")
    return options


def get_lines(filename):
	f = open(filename, 'r')
	lines = f.readlines()
	f.close()
	return lines


def compare_lines(results, expecteds):
	samelines = True
	if(len(results) != len(expecteds)):
		print("[-] Comparsion Error -> The two files have different number of lines.")
		samelines = False
	else:
		for i in range(len(results)):
			if not (compare_line(results[i], expecteds[i], i)):
				samelines = False
	return samelines


def compare_line(result_line, expected_line, i):
	global goodlines
	global badlines
	if (result_line == expected_line):
		goodlines += 1
		return True
	else:
		print("[-] Comparsion -> The ", i, ". line is different.")
		print("   ",result_line, " != ", "   ",expected_line)
		badlines += 1
		return False



options = get_arguments()
result_lines = get_lines(options.result)
expected_lines = get_lines(options.expected)
same = compare_lines(result_lines, expected_lines)

if (same):
	print("[+] Comparsion Succes -> The two files are same. -> ", goodlines, " lines are the same.")
else:
	print("[-] Comparsion Failed -> The two files are different. -> ", goodlines, " lines are same of ", len(result_lines), " lines.")
